<template>
	<center>
		<SVGBackground class='svgBG' />
	</center>
</template>

<script>

import SVGBackground from '@/assets/artwork-full.svg'
import * as d3 from 'd3'

export default {
	name: 'BackgroundManager',
	components: { SVGBackground },
	props: {
		'services': {
			type: Array,
			required: true
		}
	}, watch: {
		services: {
			handler() {
				this.updateSVG()
			}, deep: true
		}
	}, mounted() {
		// Add the black and white filter to the svg object
		d3.selectAll('.svgBG').append('filter')
			.attr('id', 'desaturate')
			.append('feColorMatrix')
			.attr('type', 'matrix')
			.attr('values', '0.3333 0.3333 0.3333 0 0 0.3333 0.3333 0.3333 0 0 0.3333 0.3333 0.3333 0 0 0 0 0 1 0')

		// Hide all the non-atributed cars (ie placeholder services, @todo remove)
		d3.selectAll('#XXX').attr('opacity', '0')
		d3.selectAll('g[data-name="XXX"]').attr('opacity', '0')


		this.services.forEach(service => {
			d3.select(`#${service.serviceName}`).attr('style', 'cursor: pointer;')
			d3.selectAll(`#${service.serviceName}`).on('click', () => { this.handleSVGClick(service) })
		})

		this.updateSVG()
	}, methods: {
		handleSVGClick(service) {
			this.$emit('clickedOnService', service)
		}, updateSVG() {
			// Set the un-finished services to greyscale, using the desaturate filter we added earlier
			this.services.forEach(service => {
				if (!service.installed) {
					d3.select(`#${service.serviceName}`).selectAll('*').attr('filter', 'url(#desaturate)')
				} else {
					d3.select(`#${service.serviceName}`).selectAll('*').attr('filter', '')
				}
			})
		}
	}
}

</script>

<style lang="scss">
	.svgBG {
		height: 100vh;
	}
</style>
