class Check
    def initialize()
		dic_path = '/lib/wnres/dict/index.sense'
	    @words = []
        @words = load_dic(dic_path)
    end

    def load_dic(dic_path)
         words =[]
        File.open(dic_path, 'r') do |f|
	        while line = f.gets
		          words << line[/.[^\%]+/]
            end
        end
    words.uniq
    end

    def is_word?(word)
        @words.include? word
    end
end

  if __FILE__ == $0
      check = Check.new
	      word = ARGV[0]
		      if check.is_word? word
			        puts "correctly"
    	      else
		       	    puts "wrong"
		      end
  end
 
