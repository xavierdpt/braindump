<template>
  <div class="hello">
    <h1>List of users</h1>
    <p>Connected: {{ numberOfConnected }}</p>
    <p></p>
    <ul>
      <li
        is="UserItem"
        :class="classUserLi"
        v-for="(user, index) in users"
        :user="user"
        :key="index"
        @click.native="editUser(index)"
      ></li>
    </ul>
    <button @click="showAddUser" v-if="!actionInProgress">Add User</button>
    <AddUser @submit="addUser" @cancel="cancelAddUser" v-if="addingUser" />
    <EditUser
      v-if="editingUser"
      :editedUser="editedUser"
      :user="users[editedUser]"
      @submit="updateUser"
      @cancel="cancelEditUser"
      @remove="removeUser"
    />
  </div>
</template>

<script>
import User from "../utils/User";
import UserItem from "./UserItem";
import AddUser from "./AddUser";
import EditUser from "./EditUser";
export default {
  name: "Users",
  components: { UserItem, AddUser, EditUser },
  data: function () {
    return {
      users: [
        User.createUser("Nicole", "Kidman", false),
        User.createUser("Ewan", "McGregor", true),
      ],
      isAddUserShown: false,
      editedUser: null,
    };
  },
  created: function () {
    console.log(
      Object.getOwnPropertyDescriptor(this.$data.users[0].name, "first")
    );
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
      return this.users[this.editedUser].name.first;
    },
    editingLastName: function () {
      return this.users[this.editedUser].name.last;
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
    addUser: function (user) {
      this.users.push(user);
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
    updateUser: function (user) {
      this.$set(this.users, this.editedUser, user);
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
