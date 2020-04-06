<template>
  <div>
    <a-card class="main-view-card">
      <SemanticAnalisForm
        @news-fetched="newsFetched"/>    
    </a-card>
    <a-card class="main-view-card">
      <a-button 
          :loading="false"
          type="primary" 
          html-type="submit" 
          @click="getAnalysis">
            Анализ
      </a-button>
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
    },
    getAnalysis(){
      this.$api.common.getAnalysis({ news : this.news}).then(({data}) => {
    })
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
