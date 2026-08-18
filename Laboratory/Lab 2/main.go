package main

import (
	"fmt"
	"log"
	"os"
	"path/filepath"

	"workspacelb2/common"
)

func main() {
	dir := "example"
	entities, err := os.ReadDir(dir)
	if err != nil { log.Fatal(err) }

	// loop entry
	for _, entry := range entities {
		if (entry.IsDir()) { return }
		path := filepath.Join(dir, entry.Name())
		// _ = path // for debug log breakpoint

		bytes, err := os.ReadFile(path)
		if err != nil { panic(err) }

		content := string(bytes)
		// _ = content // for debug log breakpoint

		// Parenthese function
		if entry.Name() == "example 1.py" {
			if common.Parenthese(content) {
				fmt.Printf("The %s is balance.\n", entry.Name())
			} else { fmt.Printf("The %s isn't balance.\n", entry.Name()) }
		}

		// Evaluation function
		if entry.Name() == "example 2.txt" && len(content) > 0 {
			fmt.Printf("Result of %s is %d.\n", entry.Name(), common.Opera(content))
		}

		// Subsets function
		if entry.Name() == "example 3.txt" && len(content) > 0 {
			fmt.Printf("Possibilty subset are %s\n", common.Subsetf(common.SubSets(common.SetParser(content))))
		}
	}
}
