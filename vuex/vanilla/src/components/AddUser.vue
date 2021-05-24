<template>
  <div>
    <h1>Add user</h1>
    <div>
      <label for="firstname">First name</label
      ><input id="firstname" v-model="firstname" />
    </div>
    <div>
      <label for="lastname">Last name</label
      ><input id="lastname" v-model="lastname" />
    </div>
    <div>
      <input id="connected" type="checkbox" v-model="connected" /><label
        for="connected"
        >Connected</label
      >
    </div>
    <button @click="addUser">Add</button>
    <button @click="cancel">Cancel</button>
    <button @click="setUser('Jim', 'Broadbent', false)">Jim Broadbent</button>
    <button @click="setUser('Richard', 'Roxburgh', true)">
      Richard Roxburgh
    </button>
  </div>
</template>
<script>
export default {
  name: "AddUser",
  data: function () {
    return { firstname: "", lastname: "", connected: true };
  },
  methods: {
    // local state updater
    setUser: function (firstname, lastname, connected) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.connected = connected;
    },
    // event emitters
    addUser: function () {
      this.$emit("submit", this.firstname, this.lastname, this.connected);
    },
    cancel: function () {
      this.$emit("cancel");
    },
  },
};
</script>