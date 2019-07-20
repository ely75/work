def miseAjourDeployParameter(app, params) {
	app.DeployEnv.each {  
		def environment = it.key
		app.DeployEnv[environment].deploy = params["Adeployer_${environment}"]
		}
}	

def checkout(app) {
	app.workspace = pwd()
	sh "echo Construction de ${app.name} ${env.BRANCH_NAME} sur `hostname` dans ${app.workspace}."
	
	deleteDir()
	checkout scm
}