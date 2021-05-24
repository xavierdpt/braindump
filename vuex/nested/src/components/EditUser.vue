<template>
  <div>
    <h1>User {{ editedUser }}</h1>
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
    <button @click="submit">Update</button>
    <button @click="remove">Delete</button>
    <button @click="cancel">Cancel</button>
    <button @click="setUser('John', 'Leguizamo', false)">John Leguizamo</button>
    <button @click="setUser('Jacek', 'Koman', true)">Jacek Koman</button>
  </div>
</template>
<script>
import User from "../utils/User";
export default {
  name: "EditUser",
  props: {
    user: { type: Object, required: true },
    editedUser: { type: Number, required: true },
  },
  data: function () {
    return {
      firstname: this.user.name.first,
      lastname: this.user.name.last,
      connected: this.user.connected,
    };
  },
  methods: {
    // local state updater
    setUser: function (firstname, lastname, connected) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.connected = connected;
    },
    // event emitters
    submit: function () {
      this.$emit(
        "submit",
        User.createUser(this.firstname, this.lastname, this.connected)
      );
    },
    cancel: function () {
      this.$emit("cancel");
    },
    remove: function () {
      this.$emit("remove");
    },
  },
};
</script>
<style scoped>
</style>