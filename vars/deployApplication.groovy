def call(Map pipelineParams) {
	pipeline {
		agent none
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