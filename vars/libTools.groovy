def miseAjourDeployParameter(app, params) {
	app.DeployEnv.each {  
		def environment = it.key
		app.DeployEnv[environment].deploy = params["Adeployer_${environment}"]
		}
}	