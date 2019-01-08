module.exports = {
	chainWebpack: config => {
		config.module.rules.delete('svg')
	},
	configureWebpack: {
		module: {
			rules: [ {
				test: /\.svg$/,
				loader: 'vue-svg-loader',
				options: {
					svgo: {
						plugins: [
							{ removeDoctype: true },
							{ removeComments: true },
							{ cleanupIDs: false },
							{ collapseGroups: false },
							{ removeEmptyContainers: false }
						]
					}
				}
			} ]
		}
	},
	devServer: {
		host: '0.0.0.0',
		port: 8584,
		disableHostCheck: true,
		https: false,
		hotOnly: false
	}
}
