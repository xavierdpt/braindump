<template>
  <li>({{ connectedString }}) {{ firstname }} {{ lastname }}</li>
</template>

<script>
export default {
  name: "User",
  props: {
    firstname: { type: String, required: true },
    lastname: { type: String, required: true },
    connected: { type: Boolean, required: true },
  },
  computed: {
    connectedString: function () {
      return this.connected ? "O" : "X";
    },
  },
};
</script>