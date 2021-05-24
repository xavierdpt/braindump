<template>
  <li>({{ connectedString }}) {{ user.name.first }} {{ user.name.last }}</li>
</template>

<script>
export default {
  name: "User",
  props: {
    user: { type: Object, required: true },
  },
  computed: {
    connectedString: function () {
      return this.user.connected ? "O" : "X";
    },
  },
};
</script>