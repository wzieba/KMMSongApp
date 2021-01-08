import SwiftUI
import domain
import local

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView {
            List(viewModel.songs){ song in
                SongRow(song: song)
            }.navigationBarTitle("Songs")
        }
    }
}

extension Song: Identifiable { }

struct SongRow: View {
    var song: Song

    var body: some View {
        HStack() {
            VStack(alignment: .leading, spacing: 10.0) {
                Text(song.name).font(.title)
                Text(song.artistName).font(.title2)
                Text(song.releaseYear).font(.title3)
            }
        }
    }
}
extension ContentView {
    class ViewModel: ObservableObject {
        
        @Published var songs = Array<Song>()
        let observeSongs: ObserveSongs
        let selectSource: SelectSource
     
        init(observeSongs: ObserveSongs, selectSource: SelectSource) {
            self.observeSongs = observeSongs
            self.selectSource = selectSource
            
            observeSongs.invoke().watch { (result) in
                self.songs = result!.songs
            }
            
            selectSource.invoke(sources: [SongSource.local, SongSource.itunes])
        }
    }
}
