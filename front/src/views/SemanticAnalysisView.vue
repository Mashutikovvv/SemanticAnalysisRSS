<template>
  <div>    
    <a-card class="main-view-card">
      <SemanticAnalisForm
        @news-fetched="newsFetched"/>    
    </a-card>
    <a-card class="main-view-card" v-if="analysisResult">      
      <a-tabs default-active-key="1" >
        <a-tab-pane key="1" tab="График">
          <Chart :datacollection="datacollection"/>   
        </a-tab-pane>
        <a-tab-pane key="2" tab="Таблица слов" force-render>
          <a-table
            :columns="columns"
            :data-source="words"
            bordered
            size="middle"/>
        </a-tab-pane>
      </a-tabs>
    </a-card>
    <a-card class="main-view-card">
      <div class="counter-wrapper" >
        <a-button 
            :loading="loading"
            type="primary" 
            html-type="submit" 
            @click="getAnalysis">
              Анализ
        </a-button>
        <div class="counter" v-if="news.length">
            Количество загруженных новостей:  
            <span>
                {{news.length || 0}}
            </span>  
        </div>
      </div>
      <NewsList :news="news"/>
    </a-card>
  </div>
</template>

<script>
export default {
  data() {
    return {      
      news: [],
      datacollection: {
        datasets: []
      },
      analysisResult: null,
      columns: [
        {
          title: 'Слово',
          dataIndex: 'word',
          sorter: (a, b) => {     
            if(a.word > b.word) {
              return 1
            }
            if(a.word < b.word) {
              return -1
            }
            return 0
          },
        },
        {
          title: 'Количество',
          dataIndex: 'count',
          sorter: (a, b) => a.count - b.count,
        },
      ],
      words:[],
      loading: false
    }    
  },
  methods: {   
    newsFetched(news) {
      this.news = news
      this.analysisResult = null
    },
    getAnalysis(){
      this.loading = true
      this.$api.common.getAnalysis({ news : this.news}).then(({data}) => {
        this.analysisResult = data
        this.datacollection.datasets = [
            {
                label: 'Слова',
                backgroundColor: '#f87979',
                pointBackgroundColor: 'white',
                borderWidth: 3,
                pointBorderColor: '#249EBF',
                data: data.terms
              },
              {
                label: 'Новости',
                backgroundColor: '#7bb6ed',
                pointBackgroundColor: 'white',
                borderWidth: 5,
                pointBorderColor: '#249EBF',
                data: data.news
              }
            ]
        this.words = data.commonTerms.map( word => Object.assign(word, {key: word.word}))
      }).finally(() => {
        this.loading = false
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
  .counter {
    margin-left: 10px ;
    span {
      font-weight: bold;
    }
    &-wrapper {
      display: flex;
      align-items: center;
    }
  }
</style>
