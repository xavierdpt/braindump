<template>
  <div class="home">
    <h1>Welcome to Memory</h1>
    <select @change="changeQuestion">
      <option v-for="(question, idx) in questions" :key="idx" :value="idx">
        {{ question.title }}
      </option>
    </select>
    <div>
      <h2>{{ currentQuestion.title }}</h2>
      <div v-for="(answer, aidx) in userAnswers" :key="aidx">
        <textarea
          :value="userAnswers[aidx]"
          :class="answerClass(aidx)"
          @input="updateUserAnswer(aidx, $event)"
          :disabled="isGood(aidx)"
          :ref="'input' + aidx"
        ></textarea>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data: function () {
    const initialState = {
      questions: [
        {
          title: "What are the seven principles of Prince2?",
          answers: [
            { text: "Continued Business Justification" },
            { text: "Learn from Experience" },
            { text: "Defined Roles and Responsibilities" },
            { text: "Manage by Stages" },
            { text: "Manage by Exception" },
            { text: "Focus on Products" },
            { text: "Tailor to the Environment" },
          ],
        },
        {
          title: "What are the seven themes of Prince2?",
          answers: [
            { text: "Business Case" },
            { text: "Organisation" },
            { text: "Quality" },
            { text: "Plans" },
            { text: "Risk" },
            { text: "Change" },
            { text: "Progress" },
          ],
        },
        {
          title: "What are the seven processes of Prince2?",
          answers: [
            { text: "Starting up a Project", abbreviation: "SU" },
            { text: "Initiating a Project", abbreviation: "IP" },
            { text: "Directing a Project", abbreviation: "DP" },
            { text: "Controlling a Stage", abbreviation: "CS" },
            { text: "Managing Product Delivery", abbreviation: "MP" },
            { text: "Managing Stage Boundaries", abbreviation: "SB" },
            { text: "Closing a Project", abbreviation: "CP" },
          ],
        },
      ],
    };
    initialState.qnum = 0;
    initialState.userAnswers = initialState.questions[
      initialState.qnum
    ].answers.map(() => "");
    initialState.focusChange = { prev: null, next: null };
    return initialState;
  },
  computed: {
    currentQuestion: function () {
      return this.questions[this.qnum];
    },
  },
  updated: function () {
    this.$nextTick(function () {
      const prevFocus = this.focusChange.prev;
      const nextFocus = this.focusChange.next;
      if (prevFocus !== nextFocus) {
        const el = this.$refs["input" + nextFocus][0];
        el.focus();
        el.scrollIntoView();
      }
    });
  },
  methods: {
    matchType: function (currentAnswer, expectedAnswer) {
      if (!currentAnswer) {
        return "blank";
      }
      if (currentAnswer === expectedAnswer) {
        return "success";
      }
      return expectedAnswer.startsWith(currentAnswer) ? "right" : "wrong";
    },
    isGood: function (idx) {
      return this.currentQuestion.answers[idx].text === this.userAnswers[idx];
    },
    updateUserAnswer: function (idx, evt) {
      const currentAnswer = evt.target.value;
      const expectedAnswer = this.currentQuestion.answers[idx].text;
      const mt = this.matchType(currentAnswer, expectedAnswer);
      if (mt !== "wrong") {
        this.focusChange = { prev: idx, next: idx };
        this.$set(this.userAnswers, idx, currentAnswer);
      } else {
        let found = false;
        for (let i = 0; i < this.currentQuestion.answers.length; ++i) {
          if (this.userAnswers[i] === "") {
            const omt = this.matchType(
              currentAnswer,
              this.currentQuestion.answers[i].text
            );
            if (omt === "right") {
              this.$set(this.userAnswers, i, currentAnswer);
              this.focusChange = { prev: idx, next: i };
              found = true;
              break;
            }
          }
        }
        if (!found) {
          this.focusChange = { prev: idx, next: idx };
          this.$set(this.userAnswers, idx, currentAnswer);
        }
      }
    },
    answerClass: function (idx) {
      const currentAnswer = this.userAnswers[idx];
      const expectedAnswer = this.currentQuestion.answers[idx].text;
      const mt = this.matchType(currentAnswer, expectedAnswer);
      if (mt === "right" || mt === "wrong") {
        return mt;
      }
      return "";
    },
    changeQuestion: function (evt) {
      this.qnum = evt.target.value;
      this.userAnswers = this.questions[this.qnum].answers.map(() => "");
    },
  },
  components: {},
};
</script>

<style lang="less" scoped>
.right {
  background-color: green;
}
.wrong {
  background-color: red;
}
.success {
  background-color: gray;
}
</style>
