template <class First, class Second>
class Pair {
public:
  First first;
  Second second;
  Pair(const First& f, const Second& s) : first(f), second(s) {}
};

// NB: "pair" is already defined in the std namespace!
