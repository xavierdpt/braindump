/**
 * http://speakingjs.com/es5/ch17.html
 */

const global = this;

// var would have the same effect
// const and let do not have the same effect
this.name = "global name";

function foobar1() {
  const thatfunction = function () {
    console.log("Hello from that function");
    /* 'this' will also be bound to the enclosing object */
    console.log(this.astring);
  };

  const myobj = {
    anumber: 7,
    astring: "hello",
    anobject: { key: "value" },
    afunction: function () {
      console.log("Hello from a function");
      /* 'this' refers to the enclosing object */
      console.log(this.astring);
    },
    afunctionreference: thatfunction,
  };
  myobj.afunction();
  myobj.afunctionreference();

  const myobj2 = {
    astring: "hallo",
    afunctionreference: thatfunction,
  };
  myobj2.afunctionreference();

  /* Explicitly bind a function to a specific object */
  thatfunction.bind(myobj2)();
}

function foobar2() {
  const jane = {
    name: "Jane",
    describe: function () {
      return "Person named " + this.name;
    },
  };
  console.log(jane.describe());
  console.log(jane.age === undefined);
  jane.age = 30;
  console.log(jane.age);
  delete jane.age;
  console.log(jane.age === undefined);

  // property exists and is equal to undefined
  jane.age = undefined;

  // property does not exist, but getting it still returns undefined
  delete jane.age;
  console.log(jane.age === undefined);

  // delete does not affect inherited properties

  // deleting properties prevent some optimization on constant shape

  // property can be deleted => true
  jane.age = 30;
  console.log(delete jane.age === true);

  // property cannot be deleted => false
  Object.defineProperty(jane, "age", {
    value: 30,
    configurable: false,
  });
  console.log(delete jane.age === false);
  console.log(jane.age);
}

function foobar3() {
  // this is valid
  const curious = {
    let: 1,
    const: 2,
    var: 3,
    function: 4,
    8: 5,
    "hello world": 6,
    foo: function () {
      return 7;
    },
  };
  console.log(curious.let);
  console.log(curious[8]);
  console.log(curious["hello world"]);
  console.log(curious[6 + 2]);
  console.log(curious["foo"]());
  curious[6 + 3] = 8;
  console.log(curious[6 + 3]);
  console.log(delete curious[6 + 3] === true);
}

function foobar4() {
  /* Object conversion */
  function io(...args) {
    if (args.length) {
      console.log({ i: args[0], o: Object(args[0]) });
    } else {
      console.log({ o: Object() });
    }
  }
  io();
  io(undefined);
  io(null);
  io(true);
  io(5);
  io("hello");
  io({});
  const myobject = { x: 5 };
  console.log(Object(myobject) === myobject);
}

function foobar5() {
  /* Check if value is an object*/
  const isObject = function (obj) {
    return Object(obj) === obj;
  };
}

function foobar6() {
  /* this is implicitly the global context */
  console.log(this === global);
}

function foobar7() {
  "use strict";
  /* this is implicitly undefined in strict mode */
  console.log(this === undefined);
}

function foobar8() {
  /* Inside an object, this refers to the object instance */
  const myobject = {
    myfunction: function (o) {
      return this === o;
    },
  };
  console.log(myobject.myfunction(myobject) === true);
}

function foobar9() {
  "use strict";
  /* This is also true in strict mode */
  const myobject = {
    myfunction: function (o) {
      return this === o;
    },
  };
  console.log(myobject.myfunction(myobject) === true);
}

function foobar10() {
  /* Here is an object with a name and a function */
  const myobject = {
    name: "myobject",
    myfunction: function (prfx, sufx) {
      return prfx + this.name + sufx;
    },
  };

  /* And here is another object with only a name */
  const myotherobject = {
    name: "myotherobject",
  };

  // standard call
  console.log(myobject.myfunction("y", "z"));

  // if the function is set to variable, the implicit this is the global one, or undefined in strict mode
  const f = myobject.myfunction;
  console.log(f("y", "z"));

  // for call, apply, and bind, f and myobject.myfunction are equivalent because the context is explicitly set
  // call with this set to myobject
  console.log(f.call(myobject, "y", "z"));

  // call with this set to myotherobject
  console.log(f.call(myotherobject, "y", "z"));

  // apply with this set to myobject
  console.log(f.apply(myobject, ["y", "z"]));

  // apply with this set to myotherobject
  console.log(f.apply(myobject, ["y", "z"]));

  // bind with one argument set on myobject
  console.log(f.bind(myobject, "y")("z"));

  // bind with one argument set on myotherobject
  console.log(f.bind(myotherobject, "y")("z"));
}

function foobar11() {
  "use strict";

  /* This function calls this */
  const MyThing = function (a, b) {
    if (this === undefined) {
      console.log("Called as a function in strict mode");
    } else {
      this.myfield = a + b;
    }
  };

  // With new, this is bound to an internal object
  const mt = new MyThing("a", "b");
  console.log(mt);

  // Without new, this is undefined in strict mode and it fails
  // and the function does not return anything anyway
  MyThing("a", "b");
}

function foobar12() {
  "use strict";

  /* This function calls this */
  const MyThing = function (a, b) {
    if (this === undefined) {
      console.log("Called as a function in strict mode");
    } else {
      this.myfield = a + b;
    }
  };

  /* Here is a new instance */
  console.log(new MyThing("a", "b"));

  /* Since MyThing is a function, we can call it with bind */
  /* The first parameter can actually be anything because it is ignored wen used with new*/
  console.log(new (MyThing.bind(null, "c", "d"))());

  /* Let's create some variables */
  const MyEFThing = MyThing.bind(null, "e", "f");
  console.log(new MyEFThing());

  /* We can also use currying */
  const MyGThing = MyThing.bind(null, "g");
  console.log(new MyGThing("h"));

  /* But bind is a function, so we can actually call it with apply */
  /* Here, MyThing is a function */
  /* Therefore, MyThing.bind is a function, which returns a function */
  /* Therefore, calling apply on MyThing.bind returns a function */
  /* And this returned function can be use as a constructor with new */
  const MyIThing = MyThing.bind.apply(MyThing, [null, "i"]);
  console.log(new MyIThing("j"));

  /* MyThing.bind is actually the "static" bind function, so this also works */
  const MyKThing = Function.prototype.bind.apply(MyThing, [null, "k"]);
  console.log(new MyKThing("l"));

  console.log(Function.prototype.bind === MyThing.bind);
}

function foobar13() {
  "use strict";

  // set foo on all functions
  // foo return the mood of the function
  Function.prototype.foo = function () {
    return this.mood;
  };

  // this is a function
  const bar = function () {};

  // set its mood as an instance variable
  bar.mood = "happy";

  // foo() says that bar is happy
  console.log(bar.foo());
}

function foobar14() {
  /* Bind always creates a new function */
  const f = function () {};
  const o = {};
  console.log(f.bind(o) === f.bind(o));
  const bound = f.bind(o);
  console.log(bound.bind(o) === bound);
}

function foobar14() {
  "use strict";

  const x = {
    name: "x",
    foo: function () {
      return (function () {
        return this ? this.name : "ohlala";
      })();
    },
  };

  // ohlala
  console.log(x.foo());

  const y = {
    name: "y",
    foo: function () {
      return (() => {
        return this ? this.name : "ohlala";
      })();
    },
  };

  // the arrow function implicitly binds to the parent when undefined
  console.log(y.foo());
}

function foobar15() {
  "use strict";
  const x = {
    name: "x",
    foo: function () {
      return this.name;
    },
  };

  // implicity bind to the object
  console.log(x.foo());

  const y = {
    name: "y",
    foo: () => {
      return this === undefined ? "ohlala" : this.name;
    },
  };

  // implicit bind to the parent's this, which is undefined
  console.log(y.foo());
}

function foobar16() {
  /* To do it probably, use normal function for implicit this and arrow function for parent this */
  "use strict";
  const x = {
    name: "x",
    foo: function () {
      return (() => {
        return this.name;
      })();
    },
  };
  console.log(x.foo());
}

function quizz() {
  "use strict";
  const quizz = {
    title: "Can you find which function(s) will return this title and why ?",
    optionA: function () {
      return (function () {
        return this == undefined ? "wrong answer" : this.title;
      })();
    },
    optionB: function () {
      return (() => {
        return this == undefined ? "wrong answer" : this.title;
      })();
    },
    optionC: () => {
      return (function () {
        return this == undefined ? "wrong answer" : this.title;
      })();
    },
    optionD: () => {
      return (() => {
        return this == undefined ? "wrong answer" : this.title;
      })();
    },
  };
  console.log(quizz.optionA()); // this one ?
  console.log(quizz.optionB()); // or maybe this one ?
  console.log(quizz.optionC()); // or possibly this one ?
  console.log(quizz.optionD()); // or that one ?
}

function foobar17() {
  const myproto = {
    magic: function () {
      if (this.foo) {
        return this.foo();
      } else if (this.bar) {
        return this.bar();
      }
    },
  };

  const myfoo = Object.create(myproto);
  myfoo.name = "I am foo";
  myfoo.foo = function () {
    return this.name;
  };

  console.log(myfoo.magic());

  const mybar = Object.create(myproto);
  mybar.text = "This is bar";
  mybar.bar = function () {
    return this.text;
  };

  console.log(mybar.magic());
}

function foobar18() {
  const myproto = {
    name: "hello",
  };
  const myfoo = Object.create(myproto, {
    foo: {
      value: function () {
        return this.name;
      },
      writable: false,
    },
  });
  console.log(myfoo.foo());
  console.log(Object.getPrototypeOf(myfoo) === myproto);
  console.log(myproto.isPrototypeOf(myfoo) === true);
  console.log(myfoo.hasOwnProperty("name") === false);
  console.log(myproto.hasOwnProperty("name") === true);
}

function foobar19() {
  const myproto = { a: "a" };

  const myfoo = Object.create(myproto, {
    b: { value: "b", writable: true, enumerable: true },
    c: { value: "c", writable: true, enumerable: false },
    d: { value: "d", writable: false, enumerable: true },
    e: { value: "e", writable: false, enumerable: false },
    f: { value: "f" }, // enumerable is false by default
  });
  myfoo.g = "g"; // here, enumerable is true

  // b c d e f g
  // all own properties
  console.log(Object.getOwnPropertyNames(myfoo));

  // b d g
  // enumerable properties
  console.log(Object.keys(myfoo));

  // inherited enumerable properties
  const p = [];
  for (const a in myfoo) {
    p.push(a);
  }
  console.log(p);

  // to iterate over all inherited properties, a custom function is required

  // in omits non-enumerable properties for iteration
  // but include them for membership test
  console.log("c" in myfoo);
}

function foobar20() {
  const myfoo = {
    hasOwnProperty: function () {
      return "I am up to no good";
    },
  };

  // bad
  console.log(myfoo.hasOwnProperty("x"));

  // better
  console.log(Object.prototype.hasOwnProperty.call(myfoo, "x"));
}

function foobar21() {
  const myfoo = {
    get x() {
      return "hello";
    },
    set x(value) {
      console.log("no");
      return "no"; // return value is ignored
    },
  };
  console.log(myfoo.x);
  console.log((myfoo.x = 5));

  // same thing with Property Descriptors
  const mybar = Object.create(Object.prototype, {
    x: {
      get: function () {},
      set: function (value) {},
      enumerable: true,
    },
  });
}

function foobar22() {
  const myfoo = Object.create(
    {},
    {
      x: {
        configurable: true,
      },
      y: {
        configurable: false,
      },
    }
  );
  myfoo.u = "u";
  console.log(delete myfoo.x === true);
  // can't delete y
  console.log(delete myfoo.y === false);

  Object.defineProperty(myfoo, "z", { value: "hello" });
  console.log(myfoo.z);

  console.log(Object.getOwnPropertyDescriptor(myfoo, "u"));

  Object.defineProperties(myfoo, { a: { value: "hello from a" } });
  console.log(myfoo.a);
}

function foobar23() {
  "use strict";
  const myproto = {};
  Object.defineProperty(myproto, "a", {
    value: "a",
    writable: false,
    configurable: false,
    enumerable: true,
  });

  // an object can overide properties that have been set on the prototype
  const myfoo = Object.create(myproto, {
    a: {
      value: "b",
      writable: true,
      configurable: true,
      enumerable: true,
    },
  });
  console.log(myfoo.a);
  myfoo.a = "c";
  console.log(myfoo.a);

  console.log(Object.getPrototypeOf(myfoo).a);
}

function run() {
  "use strict";
  const myfoo1 = {};
  // can't add properties
  // can delete properties
  Object.preventExtensions(myfoo1);
  console.log(Object.isExtensible(myfoo1) === false);

  const myfoo2 = {};
  // make all properties unconfigurable
  Object.seal(myfoo2);

  console.log(Object.isSealed(myfoo2) === true);
  // make all properties read-only
  const myfoo3 = {};
  Object.freeze(myfoo3);
  console.log(Object.isFrozen(myfoo3) === true);
}

// Layer 3: Constructors—Factories for Instances

/*
properties
accessors
internal properties
*/
