def standardTemplate(overrideApp) {
	return [
		DeployEnv: [
			val: [ name : 'recette validation', deploy: true ],
			rec: [ name : 'recette projet', deploy: false ],
			preprod: [ name : 'recette preprod', deploy: false  ]
		],
		resourcesPath : "scripts"


	] << overrideApp
}





