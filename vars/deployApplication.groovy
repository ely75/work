def call() {


	pipeline {
		agent any

		

		stages {

			stage('Build') {
				steps {
						echo " Etape de Build"
					}
			}
			
			stage('Test') {
				steps {
						echo " Etape de Test"
					}
			}	
			
			stage('Deploy') {
				steps {
						echo " Etape de Deploy"
					}
			}		

		}
	}		
}