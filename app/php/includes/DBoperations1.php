<?php
		class DbOperations1{
			private $con;
			function __construct(){
				require_once dirname(__file__).'/DBconnect1.php';
				$db=new DBconnect1();
				$this->con=$db->connect();
			}

			function createTable($box_id){
				$id=md5($box_id);
				$name="id".$box_id;
				$sql="INSERT INTO `boxes` (`box_id`, `total`) VALUES ($name, '30')";
				if ($this->con->query($sql) === false) {
					echo "error:". mysqli_error($this->con);
					return 0;
				} 
				
				$sql="CREATE TABLE ".$name." ( `products_id` VARCHAR(255) NOT NULL , UNIQUE (`products_id`))";
				if ($this->con->query($sql) === TRUE) {
					return 1;
				} 
				else {
					echo "Error creating table: " . $this->con->error;
					return 2;
				}
			}

			function addProducts($box_id,$prod_id){
				
				$name="id".$prod_id;
				$sql="INSERT INTO $box_id (`products_id`) VALUES ($prod_id)";
				if ($this->con->query($sql) === false) {
					return 0;
				} 
				else{
					return 1;
				}
			}

			function check_product($box_id,$prod_id){
					$name="id".$prod_id;
				    $result = mysqli_query($this->con,"SELECT * FROM '$name' WHERE products_id = '$box_id'");
					if(!empty($result)){
						return 0;
					}
					else{
						return 1;
					} 

			}
		}
	