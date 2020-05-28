<template>
    <div class="app-container">
      <router-view ></router-view>
    </div>
</template>

<script>
  import ru_RU from 'ant-design-vue/lib/locale-provider/ru_RU';
  import moment from 'moment';
  import 'moment/locale/ru';
  export default {
    mounted() {
      moment.locale('ru');
      this.$router.push({name: "SemanticAnalysis"})
    },
    data() {
      return {
        ru_RU,
      };
    },
  }
</script>

<style lang="scss" scoped>
  .app-container{
    display: flex;
    justify-content: center;
  }

</style>
