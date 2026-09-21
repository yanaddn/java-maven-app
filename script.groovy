def buildApp() {
    echo "building the application..."
    sh 'mvn package'
} 

def testApp() {
    echo "testing the app..."
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
