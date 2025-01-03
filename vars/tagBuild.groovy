def call(String tagName, String message = 'Build tagging') {
    echo "Tagging the build with tag: ${tagName}"
    
    // Tagging the current commit in Git
    sh """
        git config credential.helper store //configure Git to store your username and password
        git tag -a ${tagName} -m '${message}'
        git push origin ${tagName}
    """
}
