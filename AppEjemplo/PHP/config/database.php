<?php

class Database {
    private $db = null;

    public function getConexion() {        
        $filename = __DIR__ . '/../data/tareas.db';
        try {
            $this->db = new PDO('mysql' . $filename);            
            $this->db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);                         
            return $this->db;      
        } catch (PDOException $e) {
            echo json_encode("Connection error: " . $e->getMessage() . "\n" .$filename);//'{"message":"Read: ' . $query . '"}';
            die();            
        }
        
    }

    public function generateSchema() {
        $command = '
        CREATE TABLE IF NOT EXISTS task (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name VARCHAR(100) NOT NULL,
            description VARCHAR(100) NOT NULL,            
        )';

        try {
            self::$db->exec($command);
        } catch (PDOException $e) {
            die($e->getMessage());
        }
    }
}

?>
