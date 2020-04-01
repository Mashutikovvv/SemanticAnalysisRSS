import Vue from 'vue'
Vue.use({
	toggle(state) {
		//if(process.env.NODE_ENV === 'development') return;

		if(state) {
			$.blockUI()
		} else {
			$.unblockUI()
		}
	},
	install(Vue) {
		Vue.prototype.$preloader = this.toggle
	}
})