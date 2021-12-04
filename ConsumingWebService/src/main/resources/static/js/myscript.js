function getStudent(id) {

	if (document.getElementById("student" + id).innerHTML=="") {
		// document.getElementById("student" + id).innerHTML="Hello";
		
		fetch("/getStudent/" + id)
			.then(data => data.json())
			.then(function(data){
				var textToDisplay = "";
				textToDisplay += "ID: " + data.student.id + "<br>";
				textToDisplay += "Name: " + data.student.name + "<br>";
				textToDisplay += "Grade: " + data.student.grade + "<br>";
				textToDisplay += "Letter Grade: " + data.student.letterGrade + "<br>";
				document.getElementById("student" + id).innerHTML=textToDisplay;
			});
			
		
	} else {
		document.getElementById("student" + id).innerHTML="";
	}
}