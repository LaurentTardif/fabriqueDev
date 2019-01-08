<template>
	<div class='modalBody'>
		<h1>
			<img
				:src='service.serviceIconUri'
				class='serviceIcon' />
			{{ service.serviceName }}
			<h6 class='modalSubtitle'>
				{{ service.serviceDescriptionShort }}
			</h6>

			<span
				v-if='service.installed'
				class='modalInstallStatus greenMsg'>
				Installé
			</span>
			<span
				v-if='!service.installed'
				class='modalInstallStatus redMsg'>
				Pas installé
			</span>

		</h1>

		<hr />

		<div
			v-html='marked(service.serviceMdInstallationGuide || "")' />

		<hr />

		<div
			v-if='loading'
			class='modalTestWrapper'>
			<div class='msgPostInstall'>
				Vérification de l'installation en cours...
			</div>
		</div>

		<div
			v-if='error'
			class='modalTestWrapper'>
			<div class='msgPostInstall redMsg'>
				Oops! Il semblerait que votre installation ne soit pas correcte :(
			</div>
		</div>

		<div
			v-if='success'
			class='modalTestWrapper'>
			<div class='msgPostInstall greenMsg'>
				Installation fonctionelle ! Bravo :D
			</div>
		</div>

		<div
			v-if='!loading'
			class='modalTestWrapper'>
			<h3>Testez votre installation:</h3>

			<input
				v-model='serviceURI'
				placeholder='http://your-url.com'
				type='url' />

			<button
				@click='handleTest'>
				Tester
			</button>
		</div>
	</div>
</template>

<script>

import marked from 'marked'
import axios from 'axios'

export default {
	name: 'ServiceModalBody',
	props: {
		'service': {
			type: Object,
			required: true
		}
	}, data() {
		return {
			serviceURI: '',
			error: false,
			loading: false,
			success: false
		}
	}, methods: {
		marked(data) {
			return marked(data)
		}, handleTest() {
			this.loading = true
			this.error = false
			this.success = false
			axios.post('http://api.snow.ci:8010/api/services', {
				serviceURI: this.serviceURI,
				serviceName: this.service.serviceName
			}).then(res => {
				this.loading = false
				if (res.data.result && !res.data.error) {
					this.success = true
					this.$emit('serviceIsUp', this.service.serviceName)
				} else {
					this.error = true
				}
				console.log(res)
			}).catch(err => {
				console.error(err)
				this.error = true
				this.loading = false
			})
		}
	}
}

</script>

<style lang="scss">
	.modalBody {
		background:rgb(35, 50, 55);
		color: white;
		padding: 20px;
	}

	.modalSubtitle {
		display: inline;
	}

	.serviceIcon {
		margin-bottom: -10px;
		height: 40px;
		margin-right: 10px;
		width: auto;
	}

	.modalTestWrapper > input {
		width: 85%;
		height: 20px;
		border-radius: 3px;
		margin: 15px;
	}

	.modalTestWrapper > button {
		width: 8%;
		height: 26px;
		border-radius: 5px;
		margin: 15px;
	}

	.modalInstallStatus {
		float: right;
		margin-right: 10px;
	}

	.msgPostInstall {
		font-size: 2em;
		text-align: center;
		margin: 40px;
	}

	.redMsg {
		color: #D8000C;
	}

	.greenMsg {
		color: #4BB543;
	}
</style>
