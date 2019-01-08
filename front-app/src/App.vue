<template>
	<div id='app'>
		<p
			v-if='services.length === 0 && error.length === 0'
			class='bigMessage'>
			Chargement...
		</p>

		<p
			v-if='services.length === 0 && error.length !== 0'
			class='bigMessage'>
			Une erreur s'est produite :-(
		</p>

		<modal
			:scrollable='true'
			name='serviceModal'
			height='auto'
			width='75%'>

			<ServiceModalBody
				:service='currentService'
				@serviceIsUp='serviceIsUp' />
		</modal>

		<Progress
			:services='services'
			class='onTopProgress' />

		<BackgroundManager
			v-if='services.length > 0 && error.length === 0'
			:services='services'
			@clickedOnService='onClickChildService' />
	</div>
</template>

<script>

import Vue from 'vue'

import BackgroundManager from '@/components/BackgroundManager'
import ServiceModalBody from '@/components/ServiceModalBody'
import Progress from '@/components/Progress'

import axios from 'axios'
import marked from 'marked'

export default {
	name: 'App',
	components: { BackgroundManager, ServiceModalBody, Progress },
	data() {
		return {
			services: [],
			error: [],
			currentService: {}
		}
	},
	mounted() {
		axios.get('http://api.snow.ci:8010/api/services')
			.then(data => Vue.set(this, 'services', data.data))
			.catch(err => Vue.set(this, 'error', err))
	}, methods: {
		onClickChildService(service) {
			Vue.set(this, 'currentService', service)
			this.$modal.show('serviceModal')
		}, marked(data) {
			return marked(data)
		}, serviceIsUp(serviceName) {
			const service = this.services
			const index = service.findIndex(s => s.serviceName === serviceName)
			service[index].installed = true
			Vue.set(this, 'services', this.services)
		}
	}
}

</script>

<style lang="scss">
	html, body, #app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		background-color: seagreen;
		height: 100%;
		padding: 0;
		overflow: hidden;
		margin: 0;
	}

	.bigMessage {
		color: white;
		margin-top: calc(50vh - 100px);
		font-size: 100px;
	}

	.onTopProgress {
		background: red;
		position: absolute;
		top: 0;
		left: 15%;
		right: 15%;
	}
</style>
