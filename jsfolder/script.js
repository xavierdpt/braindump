"use strict";

let resizeHandle = null;
let resizeContext = null;
let modelSynchronizer = null;
let outsideMouseHandler = null;
let conditionalClicks = true;

const logged = {};
const logOnce = (msg) => {
  if (!logged[msg]) {
    console.log(msg);
    logged[msg] = true;
  }
};

const toUnit = function (str) {
  const lastDigitIndex = Math.max(
    ..."0123456789".split("").map((digit) => str.lastIndexOf(digit))
  );
  return {
    value: +str.substring(0, lastDigitIndex + 1),
    unit: str.substring(lastDigitIndex + 1),
  };
};

const OutsideMouseHandler = function () {
  this.mousedown = [];
  this.mousemove = [];
  this.mouseup = [];
  this.click = [];
  document.addEventListener("mousedown", (e) => {
    this.disableNextClick = false;
    for (const handler of this.mousedown) {
      handler(e);
    }
  });
  document.addEventListener("mousemove", (e) => {
    for (const handler of this.mousemove) {
      handler(e);
    }
  });
  document.addEventListener("mouseup", (e) => {
    for (const handler of this.mouseup) {
      if (conditionalClicks) {
        /*
        This is an attempt at managing the following situation:

        - Step 1: Click on a cell to enter edit mode
        -- Then you can  click anywhere to leave the edit mode and commit the change, but do not do that yet
        - Step 2: Drag the vertical pink resize bar, and make sure the cell is still in edit mode
        - Step 3: Release the mouse somewhere to the right of the pink resize bar

        What will happen in this case is the following:
        - 'mouseup' will fire, and the resize mode will stop
        - but 'click' will fire too, and the cell will be updated

        Desired behaviour is that any click on the screen will leave edit mode,
        but the box can be resized while the text is being edited

        To do this, the resize mouseup event handler has a disableNextClick property,
        and when we see that property, the next click is disabled

        mousemove and mouseup events are attached when clicking on the resize handle
        and detached when resizing is done
       */
        this.disableNextClick |= handler.disableNextClick;
      }

      handler(e);
    }
  });
  document.addEventListener("click", (e) => {
    if (this.disableNextClick) {
      this.disableNextClick = false;
      return;
    }
    if (!this.mouseup.length) {
      for (const handler of this.click) {
        handler(e);
      }
    }
  });
};
OutsideMouseHandler.prototype.addEventListener = function (type, handler) {
  let eventHandlers = this;
  switch (type) {
    case "mousemove":
    case "mouseup":
    case "click":
      eventHandlers = this[type];
      break;
    default:
      logOnce(`Not supported outside mouse handler for event type ${type}`);
  }
  if (eventHandlers) {
    eventHandlers.push(handler);
  }
};
OutsideMouseHandler.prototype.removeEventListener = function (type, handler) {
  let eventHandlers = this;
  switch (type) {
    case "mousemove":
    case "mouseup":
    case "click":
      eventHandlers = this[type];
      break;
    default:
      logOnce(`Outside mouse handler not supported for event type ${type}`);
  }
  if (eventHandlers) {
    this[type] = eventHandlers.filter((h) => h !== handler);
  }
};

const ResizeContext = function (container) {
  this.container = container;
  this.handlers = {
    mousemove: (e) => {
      e.preventDefault();
      this.resize(e.clientX);
    },
    mouseup: (e) => {
      this.resize(e.clientX);
      outsideMouseHandler.removeEventListener(
        "mousemove",
        this.handlers.mousemove
      );
      outsideMouseHandler.removeEventListener("mouseup", this.handlers.mouseup);
    },
  };
  this.handlers.mouseup.disableNextClick = true;
  return (e) => {
    this.start = e.clientX;
    this.initWidth = toUnit(window.getComputedStyle(container).width).value;
    outsideMouseHandler.addEventListener("mousemove", this.handlers.mousemove);
    outsideMouseHandler.addEventListener("mouseup", this.handlers.mouseup);
  };
};
ResizeContext.prototype.resize = function resize(end) {
  const { initWidth, start, container } = this;
  container.style.width = `${initWidth - 2 * (start - end)}px`;
};

const ModelSynchronizer = function (content) {
  this.content = content;
};
ModelSynchronizer.prototype.update = function () {
  const jsonmodel = document.getElementById("jsonmodel");
  for (const node of jsonmodel.childNodes) {
    jsonmodel.removeChild(node);
  }
  jsonmodel.appendChild(
    document.createTextNode(JSON.stringify(this.content, null, 2))
  );
};

const EditingContext = function (box, item) {
  this.target = box;
  this.item = item;
  this.editing = false;
  this.handlers = {
    finishEdit: (e) => {
      if (e.target !== this.input && this.editing) {
        this.editing = false;
        const newText = this.input.value;
        this.target.removeChild(this.target.childNodes[0]);
        this.target.appendChild(document.createTextNode(newText));
        this.item.title = newText;
        outsideMouseHandler.removeEventListener(
          "click",
          this.handlers.finishEdit
        );
        modelSynchronizer.update();
      }
    },
  };
  return () => {
    if (this.editing) {
      return;
    }
    this.editing = true;
    this.target.removeChild(this.target.childNodes[0]);
    this.input = document.createElement("input");
    this.input.value = this.item.title;
    this.target.appendChild(this.input);
    this.input.focus();
    setTimeout(() => {
      outsideMouseHandler.addEventListener("click", this.handlers.finishEdit);
    });
  };
};

const createBox = (item, target, depth) => {
  const div = document.createElement("div");
  div.className = "box";
  div.style.paddingLeft = `${1 + 1 * depth}rem`;
  div.appendChild(document.createTextNode(item.title));
  target.appendChild(div);
  div.addEventListener("click", new EditingContext(div, item));
};

const generateDOM = (content, target, depth = 0) => {
  for (const it of content) {
    switch (it.type) {
      case "folder":
        createBox(it, target, depth);
        generateDOM(it.items, target, depth + 1);
        break;
      case "item":
        createBox(it, target, depth);
        break;
      default:
        logOnce(`Unknown item type ${it.type}`);
    }
  }
};

window.addEventListener("load", () => {
  const content = [
    {
      type: "folder",
      title: "Folder A",
      items: [
        { type: "item", title: "Item A1" },
        { type: "item", title: "Item A2" },
        {
          type: "folder",
          title: "Folder A",
          items: [
            { type: "item", title: "Item A1" },
            { type: "item", title: "Item A2" },
            {
              type: "folder",
              title: "Folder A",
              items: [
                { type: "item", title: "Item A1" },
                { type: "item", title: "Item A2" },
              ],
            },
          ],
        },
      ],
    },
    {
      type: "folder",
      title: "Folder B",
      items: [
        { type: "item", title: "Item B1" },
        { type: "item", title: "Item B2" },
        { type: "item", title: "This is a very long item name" },
      ],
    },
    { type: "item", title: "Item C" },
  ];

  const container = document.getElementById("container");
  generateDOM(content, container);

  resizeHandle = document.getElementById("resize-handle");

  resizeContext = new ResizeContext(container);
  resizeHandle.addEventListener("mousedown", resizeContext);

  modelSynchronizer = new ModelSynchronizer(content);
  modelSynchronizer.update();

  outsideMouseHandler = new OutsideMouseHandler();

  document
    .getElementById("conditionalclicks")
    .addEventListener("change", () => {
      conditionalClicks = !conditionalClicks;
    });
});
