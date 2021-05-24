<template>
  <div class="hello">
    <h1>List of users</h1>
    <p>Connected: {{ numberOfConnected }}</p>
    <p></p>
    <ul>
      <li
        is="User"
        :class="classUserLi"
        v-for="(user, index) in users"
        :firstname="user.firstname"
        :lastname="user.lastname"
        :connected="user.connected"
        :key="index"
        @click.native="editUser(index)"
      ></li>
    </ul>
    <button @click="showAddUser" v-if="!actionInProgress">Add User</button>
    <AddUser @submit="addUser" @cancel="cancelAddUser" v-if="addingUser" />
    <EditUser
      v-if="editingUser"
      :firstname="editingFirstName"
      :lastname="editingLastName"
      :connected="editingConnected"
      :editedUser="editedUser"
      @submit="updateUser"
      @cancel="cancelEditUser"
      @remove="removeUser"
    />
  </div>
</template>

<script>
import User from "./User";
import AddUser from "./AddUser";
import EditUser from "./EditUser";
export default {
  name: "Users",
  components: { User, AddUser, EditUser },
  data: function () {
    return {
      users: [
        { firstname: "Nicole", lastname: "Kidman", connected: false },
        { firstname: "Ewan", lastname: "McGregor", connected: true },
      ],
      isAddUserShown: false,
      editedUser: null,
    };
  },
  computed: {
    numberOfConnected: function () {
      return this.users.filter((x) => x.connected).length;
    },
    editingUser: function () {
      return this.editedUser !== null;
    },
    addingUser: function () {
      return this.isAddUserShown;
    },
    editingFirstName: function () {
      return this.users[this.editedUser].firstname;
    },
    editingLastName: function () {
      return this.users[this.editedUser].lastname;
    },
    editingConnected: function () {
      return this.users[this.editedUser].connected;
    },
    actionInProgress: function () {
      return this.addingUser || this.editingUser;
    },
    classUserLi: function () {
      return { ["user-li-can-edit"]: !this.actionInProgress };
    },
  },
  methods: {
    addUser: function (firstname, lastname, connected) {
      this.users.push({
        firstname,
        lastname,
        connected,
      });
      this.isAddUserShown = false;
    },
    cancelAddUser: function () {
      this.isAddUserShown = false;
    },
    showAddUser: function () {
      this.isAddUserShown = true;
    },
    editUser: function (index) {
      if (this.editedUser === null) {
        this.editedUser = index;
      }
    },
    updateUser: function (firstname, lastname, connected) {
      this.users[this.editedUser].firstname = firstname;
      this.users[this.editedUser].lastname = lastname;
      this.users[this.editedUser].connected = connected;
      this.editedUser = null;
    },
    cancelEditUser: function () {
      this.editedUser = null;
    },
    removeUser: function () {
      this.users.splice(this.editedUser, 1);
      this.editedUser = null;
    },
  },
};
</script>

<style scoped>
.user-li-can-edit:hover {
  background: lightgray;
  cursor: pointer;
}
</style>
