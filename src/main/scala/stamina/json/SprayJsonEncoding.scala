package stamina
package json

import spray.json._

object SprayJsonEncoding extends SprayJsonFormats {
  implicit def bridge[T: RootJsonFormat]: Encoding[T] = new Encoding[T] {
    private val format = implicitly[RootJsonFormat[T]]
    def encode(t: T): ByteString = ByteString(format.write(t).compactPrint)
    def decode(bytes: ByteString): T = format.read(JsonParser(ParserInput(bytes.toArray)))
  }
}