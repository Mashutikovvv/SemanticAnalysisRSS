<template>
    <a-form-model
        ref="dynamicValidateForm"
        :model="dynamicValidateForm">
        <a-form-model-item
            v-for="(domain, index) in dynamicValidateForm.URLs"
            :key="domain.key"           
            :label="index === 0 ? 'Источники новостей' : ''"
            :prop="'URLs.' + index + '.value'"
            :rules="rules">
        <a-input
            v-model="domain.value"
            placeholder="введите сылку на RSS ленты новостей"
            style="width: 60%; margin-right: 8px"
        />
        <a-icon
            v-if="dynamicValidateForm.URLs.length > 1"
            class="dynamic-delete-button"
            type="minus-circle-o"
            :disabled="dynamicValidateForm.URLs.length === 1"
            @click="removeURL(domain)"
        />
        </a-form-model-item>
        <a-form-model-item>
        <a-button type="dashed" style="width: 60%" @click="addURL">
            <a-icon type="plus" /> Добавить источник
        </a-button>
        </a-form-model-item>
        <a-form-model-item>
        <a-button type="primary" html-type="submit" @click="submitForm('dynamicValidateForm')">
            Загрузить новости
        </a-button>
        <a-button style="margin-left: 10px" @click="resetForm('dynamicValidateForm')">
            Очистить
        </a-button>
        </a-form-model-item>
    </a-form-model>
</template>

<script>
let id = 0;
export default {
  data() {
    return {      
      dynamicValidateForm: {
        URLs: [],
      },
      rules: {
            required: true,
            message: 'Поле обязательно для заполнения',
            trigger: 'blur',
        }
    };
  },
  methods: {
    submitForm(formName) {
        let vm = this
        vm.$refs[formName].validate(valid => {
        if (valid) {
            vm.$api.common.getNews(vm.dynamicValidateForm.URLs.map(url => url.value)).then(({data}) => {
                alert('submit!', data);
            })
            
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    removeURL(item) {
      let index = this.dynamicValidateForm.URLs.indexOf(item);
      if (index !== -1) {
        this.dynamicValidateForm.URLs.splice(index, 1);
      }
    },
    addURL() {
      this.dynamicValidateForm.URLs.push({
        value: '',
        key: Date.now(),
      });
    },
  },
};
</script>
<style>
.dynamic-delete-button {
  cursor: pointer;
  position: relative;
  top: 4px;
  font-size: 24px;
  color: #999;
  transition: all 0.3s;
}
.dynamic-delete-button:hover {
  color: #777;
}
.dynamic-delete-button[disabled] {
  cursor: not-allowed;
  opacity: 0.5;
}
</style>