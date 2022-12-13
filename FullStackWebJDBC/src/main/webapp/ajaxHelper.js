/**
 * 
 */
  

	 const deleteFunction = async (id) => {
		let obj = {id: id};
		const data = await fetch("http://localhost:8080/FullStackWebJDBC/deleteStudent", {
			method: "POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(obj)
		})
		const datajson = await data.json();
		console.log(datajson);
		if(datajson == 1) {
			alert("student deleted successfully");
			getStudentList();
		} else if(datajson == 0) {
			alert("nothing deleted");
		} else {
			alert("deleting failed");
		}
	}

	 
	
	 
	 const getStudentList = async() => {
		let body = document.getElementById("databody");
		const data = await fetch("http://localhost:8080/FullStackWebJDBC/Studentlist");
		console.log(data);
		const datajson = await data.json();
		console.log(datajson);
		printStudent(datajson, body);
	}

	const printStudent = (students, body) => {
		body.innerHTML = ""
		
		for(let i = 0; i < students.length; i++) {
			let student = students[i];
			console.log(student)
			let row = document.createElement("TR");
			row.setAttribute("id", "row");
			row.innerHTML = `
				<td>${student.id}</td>
				<td>${student.firstname}</td>
				<td>${student.lastname}</td>
				<td>${student.streetAddress}</td>
				<td>${student.postCode}</td>
				<td>${student.postOffice}</td>
				
				`
			let cell = document.createElement("TD");	
			let button = document.createElement("BUTTON");
			
			button.innerText = `Delete`;
			button.addEventListener("click", () => {
				alert("delete student");
				deleteFunction(student.id);
			})
			cell.appendChild(button);
			row.appendChild(cell);
			body.appendChild(row);
		}
		
	} 
	const addStudentToBackend = async (student) => {
		console.log(student);
		const data = await fetch("http://localhost:8080/FullStackWebJDBC/addStudent", {
			method: "POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(student)
		})
		const datajson = await data.json();
		console.log(datajson);
		return datajson;
	}

	const addStudent = async ( student) => {
		
			
			console.log(student);
			const result = await addStudentToBackend(student);
			if(result == 1) {
				alert("student added successfully")
				window.location.replace("http://localhost:8080/FullStackWebJDBC/studentlist.html")
			} else if(result == 0) {
				alert("nothing added")
			}else  {
				alert("adding failed")
			}
			
		}
	
	 if(document.URL == "http://localhost:8080/FullStackWebJDBC/studentlist.html") {
		
		getStudentList();
	
	}
	
	 if(document.URL == "http://localhost:8080/FullStackWebJDBC/AddStudent.html") {
		
	const button = document.getElementById("button");
	const form = document.getElementById("form");
	const id = document.getElementById("id");
	const firstname = document.getElementById("firstname");
	const lastname = document.getElementById("lastname");
	const streetAddress = document.getElementById("streetaddress");
	const postCode = document.getElementById("postcode");
	const postOffice = document.getElementById("postoffice");
	const cancelButton = document.getElementById("cancel");
	
	cancelButton.addEventListener("click", ()=> {
		window.location.replace("http://localhost:8080/FullStackWebJDBC/studentlist.html")
	})
	console.log(streetAddress.value);
	const student = {
			id: id.value,
			firstname: firstname.value,
			lastname: lastname.value,
			streetAddress: streetAddress.value,
			postCode: postCode.value,
			postOffice: postOffice.value
	}
	
	
	form.addEventListener("submit", (e) => {
		e.preventDefault();
		const student = {
			id: id.value,
			firstname: firstname.value,
			lastname: lastname.value,
			streetAddress: streetAddress.value,
			postCode: postCode.value,
			postOffice: postOffice.value
	}
		addStudent( student)
	})
	
	}