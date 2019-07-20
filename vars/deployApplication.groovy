def call(Map pipelineParams) {
	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						sh 'echo  "Etape de Build {pipelineParams[build]}" '
					}
			}
			
			stage('Test') {
				steps {
						
						sh 'echo  "Etape de test {pipelineParams[test]}" '
						
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