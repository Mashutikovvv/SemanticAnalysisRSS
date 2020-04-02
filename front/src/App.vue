<template>
  <div class="app-container">
    <router-view ></router-view>
  </div>
</template>

<script>
  export default {

    mounted() {
        this.$router.push({name: "SemanticAnalysis"})
    }

  }
</script>

<style lang="scss" scoped>
  .app-container{
    display: flex;
    justify-content: center;
  }

</style>
