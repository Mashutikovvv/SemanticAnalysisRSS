<template>
  <div>    
    <a-card class="main-view-card">
      <SemanticAnalisForm
        @news-fetched="newsFetched"/>    
    </a-card>
    <a-card class="main-view-card" v-if="analysisResult">
      <Chart :datacollection="datacollection"/>   
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
      newss: [
        {
          title: "Британская полиция знает о местонахождении основателя WikiLeaks"
        },
        {
          title: "В суде США начинается процесс против россиянина, рассылавшего спам"
        },
        {
          title: "Церемонию вручения Нобелевской премии мира бойкотируют 19 стран"
        },
        {
          title: "В Великобритании арестован основатель сайта Wikileaks Джулиан Ассандж"
        },
        {
          title: "Украина игнорирует церемонию вручения Нобелевской премии"
        },
        {
          title: "Шведский суд отказался рассматривать апелляцию основателя Wikileaks"
        },
        {
          title: "НАТО и США разработали планы обороны стран Балтии против России"
        },
        {
          title: "Полиция Великобритании нашла основателя WikiLeaks, но, не арестовала"
        },
        {
          title: "В Стокгольме и Осло сегодня состоится вручение Нобелевских премий"
        },
      ],
      datacollection: {
        datasets: []
      },
      analysisResult: null,
    };
  },
  methods: {   
    newsFetched(news) {
      this.news = news
    },
    getAnalysis(){
      this.$api.common.getAnalysis({ news : this.news}).then(({data}) => {
        this.analysisResult = data
        this.datacollection.datasets.push(
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
        )
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
