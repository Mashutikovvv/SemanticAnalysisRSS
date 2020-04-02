<template>
  <div>
    <a-card class="main-view-card">
      <SemanticAnalisForm
        @news-fetched="newsFetched"/>    
    </a-card>
    <a-card class="main-view-card">
      <NewsList :news="news"/>
    </a-card>
  </div>
</template>

<script>
export default {
  data() {
    return {      
      news: [],
    };
  },
  methods: {   
    newsFetched(news) {
      this.news = news
    }
  },
};
</script>

<style lang="scss" scoped>
  .main-view-card {
    margin: 30px;
    width: 1200px;
  }
</style>
