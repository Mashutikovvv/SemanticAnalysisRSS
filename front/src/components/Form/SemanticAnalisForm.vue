<template>
    <a-form-model>
        <a-week-picker 
          :disabled-date="disabledDate"
          placeholder="Выберите неделю" 
          v-model="weekDate" 
          @change="onChange" />  
        <a-input-search placeholder="Ключевое слово" style="width: 200px" v-model="model.q"/>  
        <a-form-model-item>
        <a-button 
          :loading="loading"
          type="primary" 
          html-type="submit" 
          @click="submit()">
            Загрузить новости
        </a-button>
        <a-button style="margin-left: 10px" @click="resetForm">
            Очистить
        </a-button>
        </a-form-model-item>     
    </a-form-model>
</template>

<script>
let id = 0;
import moment from 'moment';
export default {
  data() {
    return {
      model: {
        q: "",
        from: "",
        to: "",
      },      
      weekDate: null,   
      news: null, 
      loading: false,
    };    
  },
  methods: {
    submit() {
      this.loading = true,
      this.$api.common.getNews(this.model).then(({data}) => {
          this.news = data
          this.$emit('news-fetched', data)
      }).finally(() => {
        this.loading = false
      })
    },
    resetForm() {
      this.model.q = ""
      this.model.from = ""
      this.model.to = ""
      this.weekDate = ""
    },
    onChange(date, dateString) {
      if(date) {
        let olderDate = moment().subtract('month', 1).add('days', 1);
        this.model.from = date.clone().weekday(0).format('yyyy-MM-DD')
        this.model.to = date.clone().weekday(6).format('yyyy-MM-DD')
        if ( olderDate.valueOf() > date.clone().weekday(0).valueOf()  ) {
          this.model.from = olderDate.format('yyyy-MM-DD')
        }
      } else {
        this.model.from = ""
        this.model.to = ""
      }
      
    },
    disabledDate(value) {
      let now = moment();
      return now.valueOf() <= value.valueOf() || now.subtract('month', 1).valueOf() >= value.valueOf()
    },
  },
};
</script>
<style lang="scss" scoped>
.dynamic-delete-button {
  cursor: pointer;
  position: relative;
  top: 4px;
  font-size: 24px;
  color: #999;
  transition: all 0.3s;
  &:hover {
    color: #777;
  }
}
/deep/ .ant-form-item-children {
  display: flex;
  .ant-input +.anticon {
    margin-left: 8px;
  }
}
.ant-calendar-picker, .ant-input-search{
  margin-bottom: 10px;
  margin-right: 10px
}
</style>