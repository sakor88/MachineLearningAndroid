# MachineLearningAndroid

Użycie modelu opartego na CNN - Convolutional Neural Networks na Androidzie

Model, kod trenowania i testowania modelu:
https://github.com/sakor88/MachineLearningPython

W katalogu Assets projektu androidowego znajduje się już przetrenowany model.
Aby przetrenować model ponownie należy w pliku projekt.py zakomentować możliwość używania CUDA oraz odkomentować #torch.save(model, 'nowymodel.pt') na dole pliku.
Następnie użyć skryptu transform.py i wyjściowy plik nowymodelandroid.pt zamienić w katalogu Assets projektu aplikacji na androida.

Model jest napisany na bazie modelu CNN przedstawionego tu: https://medium.com/swlh/building-a-deep-learning-model-with-pytorch-to-classify-fruits-and-vegetables-30e1a8ffbe8c
Wyróżnia się on ponad 99% skutecznością na validation datasecie, co daje około 90-95% skuteczność na testowym: https://ibb.co/YBP55ff
Poza tym udało się zmniejszyć rozmiar modelu do nieco poniżej 8MB.


Niestety z powodu problemów z przeniesieniem modelu na system Android nie działa on tak dobrze w aplikacji mobilnej.
Możemy tam zrobić własne zdjęcie, które zostanie przeskalowane do formatu 100x100 czyli takiego jak w datasecie uzywanym do trenowania oraz przekazane do klasyfikacji.

Poza tym możemy zmienić source image obiektu "owoc" ImageView na jeden z obrazów z fruits-360 i klikając process pojawi nam się klasyfikacja jako toast message.

