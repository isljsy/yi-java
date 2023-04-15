<template>
  <div>
    <word-base :id="wordId"></word-base>
    <pinyin :id="wordId"></pinyin>
    <explain :id="wordId"></explain>
  </div>
</template>

<script>
import Pinyin from "@/views/dir/word/pinyin";
import WordBase from "@/views/dir/word/wordBase";
import Explain from "@/views/dir/word/explain";
export default {
  name: "EditWord",
  components: { Explain, WordBase, Pinyin },
  dicts: ['dir_radical'],
  created() {
    this.wordId = this.$route.query.id
  },
  data() {
    return {
      wordId: ''
    }
  }
}
</script>

<style scoped>

</style>
