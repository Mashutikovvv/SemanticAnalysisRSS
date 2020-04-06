<template>
    <a-list itemLayout="horizontal" :dataSource="news" :locale="locale">
        <a-list-item slot="renderItem" slot-scope="item, index">
            <a-list-item-meta
                :description="item.description">
                <a slot="title" :href="item.link">{{item.title}}</a>       
            </a-list-item-meta>
        </a-list-item>
  </a-list>  
</template>

<script>
export default {
    props: {
        news: {
            type: Array,
            required: true
        }, 
    }, 
    data() {
        return {           
            locale: {
                emptyText: 'Здесь будут новости'
            }
        };
    },
 
};
</script>
<style lang="scss" scoped>
.ant-list-item {
    padding: 12px;
}

</style>