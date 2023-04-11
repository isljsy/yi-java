<template>
  <span @click="handleSetSize" style="font-size: 15px">{{fontSize}}</span>
</template>

<script>
export default {
  data() {
    return {
      fontSize: '默认',
      sizeOptions: [
        { label: 'Default', value: 'default', fontSize: '默认' },
        { label: 'Medium', value: 'medium', fontSize: '大' },
        { label: 'Small', value: 'small', fontSize: '中' },
        { label: 'Mini', value: 'mini', fontSize: '小' }
      ]
    }
  },
  computed: {
    size() {
      return this.$store.getters.size
    }
  },
  created() {
    this.sizeOptions.forEach(e => {
      if (e.value === this.$store.getters.size) {
        this.fontSize = e.fontSize
      }
    });
  },
  methods: {
    handleSetSize() {
      let i;
      for (i in this.sizeOptions) {
        const option = this.sizeOptions[i];
        if (option.value === this.$store.getters.size) {
          break;
        }
      }
      i = parseInt(i)
      i = i === this.sizeOptions.length - 1 ? 0 : i + 1
      const size = this.sizeOptions[i].value
      this.fontSize = this.sizeOptions[i].fontSize
      this.$ELEMENT.size = size
      this.$store.dispatch('app/setSize', size)
      this.refreshView()
      this.$message({
        message: 'Switch Size Success',
        type: 'success'
      })
    },
    refreshView() {
      // In order to make the cached page re-rendered
      this.$store.dispatch('tagsView/delAllCachedViews', this.$route)

      const { fullPath } = this.$route

      this.$nextTick(() => {
        this.$router.replace({
          path: '/redirect' + fullPath
        })
      })
    }
  }

}
</script>
