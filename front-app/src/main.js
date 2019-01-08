import Vue from 'vue'
import App from './App.vue'

import VModal from 'vue-js-modal'
import VueProgress from 'vue-progress'


Vue.use(VModal)
Vue.use(VueProgress)

Vue.config.productionTip = false

const vm = new Vue({
	render: h => h(App)
}).$mount('#app')
global.vm = vm
