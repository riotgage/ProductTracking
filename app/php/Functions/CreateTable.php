<?php
require_once '../includes/DBoperations1.php';
$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		if(isset($_POST['box_id'])){
		//operate the data further 

		$db = new DBoperations1(); 

		foreach($_POST as $key=>$value)
		{
  			$id=$value;	
		}

		$result = $db->createTable($id);
		if($result==1){
			$response['error'] = false; 
			$response['message'] = "Box added succesfully";
		}
		elseif ($result==0) {
			$response['error'] = true; 
			$response['message'] = "Box already added";
		}
		else{
			$response['error'] = false; 
			$response['message'] = "Error Creating table";
		}
	}
	else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	}


	}else{
		$response['error']=true;
		$response['message']="Invalid Request";

	}

	echo json_encode($response);	

