if sh runcrud.sh; then
   open -a Safari http://localhost:8080/crud/v1/tasks
else
   echo "There were errors"
fi