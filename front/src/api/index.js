import axios from 'axios'
import Vue from 'vue'

const VueApi = (Vue) => {
    Object.defineProperty(Vue.prototype, '$api', {
      get() {
        let api = null
        if (this && this.$root) {
		  api = this.$root.$options._base.api
		}
		
        return api || Vue.api
      }
    })
}

Vue.use(VueApi)

const
	requireService = require.context('./service', false, /.service.js$/),
	instance = axios.create({
		baseURL: `http://localhost:4040`,
		headers: {
			'Content-Type': 'application/json'
		},
		withCredentials: true
	})


class Api {
	constructor () {
		this.instance = instance

		// Register local services
		requireService.keys().forEach(filename => requireService(filename).default(this))
	}

	install (Vue) {
		Vue.api = this
	}
}

export default new Api()
