package main
import "fmt"
import "math/rand"
import "sync"
func RandomGenerator(wg *sync.WaitGroup, stop <-chan bool, m int) <-chan int {
     intStream := make(chan int)
     go func() {
          defer func() {wg.Done()}()
          defer close(intStream)
          for {
             select {
                case <-stop:
                   return
                case intStream <- rand.Intn(1000000) * m:
} }
}()
     return intStream
	}


func Multiple(x int, m int) bool {
	if(x%m == 0){
		return true
	}
	return false
}

func main(){
	var wg sync.WaitGroup
	ch:=make(chan bool)

	m5, m13, m97 := RandomGenerator(&wg,ch, 5),RandomGenerator(&wg,ch, 13),RandomGenerator(&wg,ch, 97)
	var count5, count13, count97 int

	for i := 0; i < 100; i++ {

		select {

		case multiple5 := <-m5:
			if Multiple(multiple5, 5) {
				count5++
			}

		case multiple13 := <-m13:
			if Multiple(multiple13, 13) {
				count13++
			}

		case multiple97 := <-m97:
			if Multiple(multiple97, 97) {
				count97++
			}
		}

		wg.Wait()

		
	}
	fmt.Printf("There are %d multiples of 5 \n", count5)
	fmt.Printf("There are %d multiples of 13 \n", count13)
	fmt.Printf("There are %d multiples of 97 \n", count97)


}