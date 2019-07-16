<?php
require_once '../includes/DBoperations1.php';
$response=array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		if(isset($_POST['box_id']) and isset($_POST['prod_id'])){
		//operate the data further 

		$db = new DBoperations1(); 

		$box_id=$_POST['box_id'];
  		$prod_id=$_POST['prod_id'];	
		

		$result = $db->addProducts($box_id,$prod_id);
		if($result){
			$response['error'] = false; 
			$response['message'] = "product added succesfully";
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