package main

import (
	"bufio"
	"fmt"
	"os"
	"path/filepath"
	"strings"

	"workspacelb2/common"
)

func main() {
	dir := "example"
	entities, err := os.ReadDir(dir)
	if err != nil { panic((err)) }
	
	// loop entry
	var content string
	for _, entry := range entities {
		if (entry.IsDir()) { return }
		path := filepath.Join(dir, entry.Name())
		// _ = path // for debug log breakpoint

		bytes, err := os.ReadFile(path)
		if err != nil { panic(err) }

		content = string(bytes)
		// _ = content // for debug log breakpoint

		// Parenthese function
		if strings.HasSuffix(entry.Name(), ".py") && len(content) > 0 {
			if common.Parenthese(content) {
				fmt.Printf("The %s is balance.\n", entry.Name())
				// fmt.Println("The file is balanced.")
			} else { fmt.Printf("The %s isn't balance.\n", entry.Name()) }
		}

		file, err := os.Open(path)
		if err != nil { panic(err) }; defer file.Close()
		scanner := bufio.NewScanner(file)
	
		// Evaluation function
		if strings.HasSuffix(entry.Name(), "Expression.txt") {
			fmt.Printf("Expression of %s\n", entry.Name())
			for scanner.Scan() {
				content = scanner.Text()
				fmt.Printf("Result of %s is %d.\n", content, common.Opera(content))
			}
		}

		// Subsets function
		if strings.HasPrefix(entry.Name(), "Set") {
			fmt.Printf("Subset inside %s are: \n", entry.Name())
			for scanner.Scan() {
				content = scanner.Text()
				fmt.Printf("\tPossibilty subset of %s are %s\n", content, common.Subsetf(common.SubSets(common.SetParser(content))))
			}
		}

		if scanner.Err() != nil { panic("Fatal to opening/reading a file.") }
	}
}