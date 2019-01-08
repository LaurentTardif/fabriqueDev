<template>
	<!-- <p>{{ servicesCount }}  {{ servicesUp }}</p> -->

	<div>

		<img
			class='imgProgress imgLeft'
			src='http://www.pngmart.com/files/2/Mario-PNG-Clipart.png' />

		<progress-bar
			ref='line'
			:options='lineOptions'
			class='progress'
			type='line' />

		<img
			class='imgProgress imgRight'
			src='https://vignette.wikia.nocookie.net/nintendo/images/7/77/Princess_Peach_NSMBW.png/revision/latest/scale-to-width-down/276?cb=20120107141833&path-prefix=en' />
	</div>

</template>

<script>

export default {
	name: 'BackgroundManager',
	props: {
		'services': {
			type: Array,
			required: true
		}
	}, data() {
		return {
			servicesCount: 0,
			servicesUp: 0,
			lineOptions: {
				color: '#007AFF',
				strokeWidth: 2.1,
				trailWidth: 2.1,
				duration: 400
			}
		}
	}, watch: {
		services: {
			handler() {
				this.calc()
			}, deep: true
		}
	}, methods: {
		calc() {
			const upServices = this.services.filter(s => s.installed)
			this.servicesCount = this.services.length
			this.servicesUp = upServices.length

			this.$refs.line.animate(this.servicesUp / this.servicesCount)
		}
	}
}

</script>

<style lang="scss">
	.imgProgress {
		height: 75px;
		width: auto;
		z-index: 99;
	}
	.imgLeft {
		position: absolute;
		top: 0;
		left: 0;
	}
	.imgRight {
		position: absolute;
		top: 0;
		right: 0;
	}
	.progress {
		position: absolute;
		top: 20px;
		left: 0;
		right: 0;
	}
</style>
