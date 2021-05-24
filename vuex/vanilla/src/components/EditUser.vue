<template>
  <div>
    <h1>User {{ editedUser }}</h1>
    <div>
      <label for="firstname">First name</label
      ><input id="firstname" v-model="firstname_" />
    </div>
    <div>
      <label for="lastname">Last name</label
      ><input id="lastname" v-model="lastname_" />
    </div>
    <div>
      <input id="connected" type="checkbox" v-model="connected_" /><label
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
export default {
  name: "EditUser",
  props: {
    firstname: { type: String, required: true },
    lastname: { type: String, required: true },
    connected: { type: Boolean, required: true },
    editedUser: { type: Number, required: true },
  },
  data: function () {
    return {
      firstname_: this.firstname,
      lastname_: this.lastname,
      connected_: this.connected,
    };
  },
  methods: {
    // local state updater
    setUser: function (firstname, lastname, connected) {
      this.firstname_ = firstname;
      this.lastname_ = lastname;
      this.connected_ = connected;
    },
    // event emitters
    submit: function () {
      this.$emit("submit", this.firstname_, this.lastname_, this.connected_);
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