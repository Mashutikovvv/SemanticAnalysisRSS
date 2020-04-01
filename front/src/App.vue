<template>
  <router-view ></router-view>
</template>

<script>
  export default {

    mounted() {
        this.$router.push({name: "SemanticAnalysis"})
    }

  }
</script>

<style lang="scss" scoped>

</style>
