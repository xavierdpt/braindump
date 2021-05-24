export default {
  createUser(first, last, connected) {
    return { name: { first, last }, connected };
  },
};
