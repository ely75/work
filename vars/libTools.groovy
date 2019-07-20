def miseAjourDeployParameter(app, params) {
	app.DeployEnv.each {  
		def environment = it.key
		app.DeployEnv[environment].deploy = params["Adeployer_${environment}"]
		}
}	

def checkoutAfaire(app) {
	//app.workspace = pwd()
	echo "Construction de ${app.name} ${env.BRANCH_NAME} "
	
	deleteDir()
	checkout scm
}