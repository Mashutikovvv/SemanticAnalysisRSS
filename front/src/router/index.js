import Vue from 'vue'
import VueRouter from 'vue-router'
import SemanticAnalysisView from '../views/SemanticAnalysisView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'SemanticAnalysis',
    component: SemanticAnalysisView
  },  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
