job('ejemplo-job-DSL') {
	description('Job DSL de ejemplo')	
  	scm {
    	git('https://github.com/damianbaldi/jenkins.job.parametrizado.git', 'main') {node ->
      		node / gitConfigName('damianbaldi')
      		node / gitConfigEmail('dbaldi@teco.com.ar')
    	}
  	}
  	parameters {
    	stringParam('nombre', defaultValue = 'Damian', description = 'Parametro de cadena para Job Booleano')
      	choiceParam('Planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      	booleanParam('agente', false)
    }
  	triggers {
		cron('H/7 * * * *')
    }
    steps {
      	shell("bash jobscript.sh")   
    }
  	publishers {
      	mailer('dbaldi@teco.com.ar', true, true)
      	slackNotifier {
		  notifyAborted(true)
		  notifyEveryFailure(true)
		  notifyNotBuilt(false)
		  notifyUnstable(false)
		  notifyBackToNormal(true)
		  notifySuccess(false)
		  notifyRepeatedFailure(false)
		  startNotification(false)
		  includeTestSummary(false)
		  includeCustomMessage(false)
		  customMessage(null)
		  sendAs(null)
		  commitInfoChoice('NONE')
		  teamDomain(null)
		  authToken(null)     
        }
    }
}
